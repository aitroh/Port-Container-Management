package lab2;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PortContainerManagementSystem {

	private static ArrayDeque<Container> containerStack = new ArrayDeque<>();
	private static ArrayDeque<Ship> shipQueue = new ArrayDeque<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;

		do {
			System.out.println("\n --Port Container Management System--");
			System.out.println("[1] Store container (push)");
			System.out.println("[2] View port containers");
			System.out.println("[3] Register arriving ship (enqueue)");
			System.out.println("[4] View waiting ships");
			System.out.println("[5] Load next ship (pop container + poll ship)");
			System.out.println("[0] Exit");
			System.out.print("Choose: ");
			choice = Integer.parseInt(sc.nextLine());

			if (choice == 1) {
				storeContainer();
			} else if (choice == 2) {
				viewContainers();
			} else if (choice == 3) {
				registerShip();
			} else if (choice == 4) {
				viewShips();
			} else if (choice == 5) {
				loadNext();
			} else if (choice == 0) {
				System.out.println("Exiting system... Goodbye!");

			} else {
				System.out.println("Invalid choice. Try again.");

			}
		} while (choice != 0);
	}

	private static void storeContainer() {
		System.out.print("Enter Container ID: ");
		String id = sc.nextLine();
		System.out.print("Enter Description: ");
		String desc = sc.nextLine();
		System.out.print("Enter Weight (e.g., 200kg): ");
		String weight = sc.nextLine();

		Container c = new Container(id, desc, weight);
		containerStack.push(c);
		System.out.println("\nStored: " + c);
	}

	private static void viewContainers() {
		if (containerStack.isEmpty()) {
			System.out.println("No containers at the port.");
			return;
		}
		System.out.println("\nTOP →");
		for (Container c : containerStack) {
			System.out.println(c);
		}
		System.out.println("← BOTTOM");
	}

	private static void registerShip() {
		System.out.print("Enter Ship Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Captain Name: ");
		String captain = sc.nextLine();

		Ship s = new Ship(name, captain);
		shipQueue.offer(s);
		System.out.println("\nRegistered: " + s);
	}

	private static void viewShips() {
		if (shipQueue.isEmpty()) {
			System.out.println("No ships waiting.");
			return;
		}
		System.out.println("\nFRONT →");
		for (Ship s : shipQueue) {
			System.out.println(s);
		}
		System.out.println("← REAR");
	}

	private static void loadNext() {
		if (containerStack.isEmpty()) {
			System.out.println("No containers available to load!");
			return;
		}
		if (shipQueue.isEmpty()) {
			System.out.println("No ships waiting at the dock!");
			return;
		}

		Container c = containerStack.pop(); // LIFO
		Ship s = shipQueue.poll(); // FIFO
		System.out.println("\nLoaded: " + c + " → " + s);
		System.out.println("Remaining containers: " + containerStack.size());
		System.out.println("Remaining ships waiting: " + shipQueue.size());
	}
}