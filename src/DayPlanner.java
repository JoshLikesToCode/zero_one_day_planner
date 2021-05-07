import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class DayPlanner {
    private static int max_weight;
    private static int value[];
    private static int weight[];
    private static Scanner scanner = new Scanner(System.in);


    private static ArrayList<Tasks> planner = new ArrayList<>();
    private static HashSet<String> sorted_planner = new HashSet<>();

    static void sort_through_planner() {
        value = new int[planner.size()];
        weight = new int[planner.size()];

        for (int i = 0; i < planner.size(); i++) {
            weight[i] = planner.get(i).getTime_to_complete();
            value[i] = planner.get(i).getImportance();
        }
        knap_sack_algo(max_weight, weight, value, planner.size());
    }

    static void set_max_weight()
    {
        System.out.println("How much time do you have to put towards tasks today?");
        max_weight = scanner.nextInt();
    }

    static int max(int a, int b)
    {
        return Math.max(a, b);
    }

    static void knap_sack_algo(int max_weight, int wt[], int val[], int size)
    {
        int i, w, k = 0;
        int X[][] = new int[size+1][max_weight + 1];

        for(i = 0; i <= size; i++)
        {
            for(w = 0; w <= max_weight; w++)
            {
                if (i == 0 || w == 0)
                    X[i][w] = 0;
                else if (wt[i - 1] <= w)
                {
                    X[i][w] = max(val[i - 1] + X[i - 1][w - wt[i - 1]], X[i - 1][w]);
                }
                else
                {
                    X[i][w] = X[i - 1][w];
                }
            }
        }
        int results = X[size][max_weight];
        w = max_weight;
        for(i = size; i > 0 && results > 0; i--)
        {
            if(results == X[i-1][w])
                continue;
            else
            {
                int match = wt[i-1];
                int j = 0;
                for(Tasks tasks : planner)
                {
                    if(planner.get(j).getTime_to_complete() == wt[i-1])
                    {
                        sorted_planner.add(planner.get(j).getTask_name());
                    }
                    j++;
                }
                results = results - val[i-1];
                w = w - wt[i-1];
            }
        }
    }

    public static void add_to_planner()
    {
        System.out.println("Please give me the name of your activity.");
        Scanner scanner = new Scanner(System.in);
        String activity = scanner.nextLine();
        System.out.println("Please give me the importance of this activity on a scale of 1-10.");
        int importance = scanner.nextInt();
        System.out.println("Lastly, please give me the estimated time to complete this task (this must be a unique integer).");
        int time_to_complete = scanner.nextInt();
        planner.add(new Tasks(importance, time_to_complete, activity));
    }


    public static void main(String[] args) {
        System.out.println("How many tasks do you have today?");
        Scanner scanner = new Scanner(System.in);
        int num_of_tasks = scanner.nextInt();
        set_max_weight();


        for(int i = 0; i < num_of_tasks; i++)
        {
            add_to_planner();
        }
        System.out.println("Let's print your planner.");
        for(int i = 0; i < num_of_tasks; i++)
        {
            System.out.println("=========================================");
            System.out.println("Activity #" + i + ": " + planner.get(i).getTask_name());
            System.out.println("Importance (from 0 - 1): " + planner.get(i).getImportance());
            System.out.println("Estimated time to complete: " + planner.get(i).getTime_to_complete());
        }
        System.out.println("=========================================");
        sort_through_planner();
        System.out.println("The tasks you should focus on are: " + sorted_planner);
    }
}