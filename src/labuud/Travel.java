package labuud;

import java.util.Scanner;
public class Travel {
	int [] tree;
	int size;
	int loc;
	public Travel() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the tree: ");
        size = scanner.nextInt();

        tree = new int[size + 1]; 

        System.out.println("Enter the elements of the tree:");
        for (int i = 1; i <= size; i++) {
            System.out.print("Node " + i + ": ");
            tree[i] = scanner.nextInt();
        }
        loc = size + 1;
	}
	public void inOrder(int i) {
		if(i<tree.length) {
			inOrder(i*2);
			System.out.print(tree[i] + "\t");
			inOrder(i*2+1);
		}
		
	}
	public void preOrder(int i) //1,2,4,8     
	{
		if(i<tree.length) {
			System.out.print(tree[i] + "\t");
			preOrder(i*2);
			preOrder(i*2+1);
		}
	}
	public void postOrder(int i) {
		if(i<tree.length) {
			postOrder(i*2);
			postOrder(i*2+1);	
			System.out.print(tree[i] + "\t");
		}
	}
	public void levelorder() {
        int[] queue = new int[loc+1]; 
        int front = 0, rear = 0;

        queue[rear++] = 1; 

        while (front != rear) {
            int current = queue[front++];
            System.out.print(tree[current] + "\t");

            int leftChild = current * 2;
            int rightChild = current * 2 + 1;

            if (leftChild < loc) {
                queue[rear++] = leftChild;
            }
            if (rightChild < loc) {
                queue[rear++] = rightChild;
            }
        }
        System.out.println("\n");
    }
	public void add(int x) //6
	{
		if(loc == 0)
		{
			tree[1] = x;
			loc = 2;
		}
		else
		{
			tree[loc++] = x;
			up();
		}
	}
	public void up()
	{
		int pos = loc-1; //4
		while(pos/2>0 && tree[pos]<tree[pos/2])
		{
			int temp = tree[pos];
			tree[pos] = tree[pos/2];
			tree[pos/2] = temp;
			pos = pos/2;
			
		}
	}

	public void down() {
	    int current = 1;
	    int child;

	    while (2 * current < loc) {
	        child = 2 * current; // Left child
	        if (child + 1 < loc && tree[child + 1] < tree[child]) {
	            child++;
	        }
	        if (tree[current] > tree[child]) {
	            int temp = tree[current];
	            tree[current] = tree[child];
	            tree[child] = temp;
	            current = child;
	        } else {
	            break;
	        }
	    }
	}
	public int remove()
	{if(loc==0) {
		System.out.println("Tree is empty. Cannot remove nodes.");
        return -1;
	}
		int root = tree[1];
		tree[1] = tree[loc-1];
		loc--;
		down();
		return root;
	}
	public void minimumheap() {
	    for (int i = (loc - 1) / 2; i >= 1; i--) {
	        minHeapify(i);
	    }
	}

	private void minHeapify(int i) {
	    int left = 2 * i;
	    int right = 2 * i + 1;
	    int smallest = i;

	    if (left < loc && tree[left] < tree[i]) {
	        smallest = left;
	    }

	    if (right < loc && tree[right] < tree[smallest]) {
	        smallest = right;
	    }

	    if (smallest != i) {
	        int temp = tree[i];
	        tree[i] = tree[smallest];
	        tree[smallest] = temp;
	        minHeapify(smallest);
	    }
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        Travel travel = new Travel();
      


        int choice = 0;
        do {
            System.out.println("\nBinary Tree Operations:");
            System.out.println("1. Add Node");
            System.out.println("2. Remove Root Node");
            System.out.println("3. Display In-Order Traversal");
            System.out.println("4. Display Pre-Order Traversal");
            System.out.println("5. Display Post-Order Traversal");
            System.out.println("6. Display Level-Order Traversal");
            System.out.println("7. Display Minimum heap Tree");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter value to add: ");
                    int valueToAdd = scanner.nextInt();
                    travel.add(valueToAdd);
                    System.out.println("Value " + valueToAdd + " added to the tree.");
                    break;
                case 2:
                    int removedValue = travel.remove();
                    System.out.println("Removed root node with value: " + removedValue);
                    break;
                case 3:
                    System.out.print("In-Order Traversal: ");
                    travel.inOrder(1);
                    break;
                case 4:
                    System.out.print("Pre-Order Traversal: ");
                    travel.preOrder(1);
                    break;
                case 5:
                    System.out.print("Post-Order Traversal: ");
                    travel.postOrder(1);
                    break;
                case 6:
                    System.out.print("Level-Order Traversal: ");
                    travel.levelorder();
                    break;
                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;
                case 7:
                    travel.minimumheap();
                    System.out.print("Tree Content in Minimum Heap Form: ");
                    for (int i = 1; i < travel.loc; i++) {
                        System.out.print(travel.tree[i] + " ");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 8);

        scanner.close();
    }
}
	
	
