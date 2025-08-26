package com.solid.principles;

public class DInterfaceSegregation {

//	The Liskov Substitution Principle (LSP) states that objects of a superclass should be 
//	replaceable with objects of a subclass without affecting the correctness of the program. 
//	Essentially, subclasses should be able to stand in for their parent classes.

    public static void main(String[] args) {
        // Violating ISP
        System.out.println("Violating ISP:");
        Machine oldPrinter = new OldPrinter();
        oldPrinter.print();
        try {
            oldPrinter.scan();
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Following ISP
        System.out.println("\nFollowing ISP:");
        Printer simplePrinter = new SimplePrinter();
        simplePrinter.print();

        Scanner simpleScanner = new SimpleScanner();
        simpleScanner.scan();

        MultiFunctionMachine mfd = new MultiFunctionMachine();
        mfd.print();
        mfd.scan();
    }

}

//Violating ISP
//Here, we have a single interface that forces all devices to implement both print() and scan():

interface Machine {
	void print();

	void scan();
}

class OldPrinter implements Machine {
	@Override
	public void print() {
		System.out.println("Printing...");
	}

	@Override
	public void scan() {
		throw new UnsupportedOperationException("Scan not supported");
	}
}

//Problem:
//OldPrinter only supports printing, but is forced to implement scan().
//This violates ISP because it's forced to depend on a method it doesn't use.

//Following ISP
//Split the interface into smaller, focused ones:
interface Printer {
	void print();
}

interface Scanner {
	void scan();
}

class SimplePrinter implements Printer {
	@Override
	public void print() {
		System.out.println("Printing...");
	}
}

class SimpleScanner implements Scanner {
	@Override
	public void scan() {
		System.out.println("Scanning...");
	}
}

class MultiFunctionMachine implements Printer, Scanner {
	@Override
	public void print() {
		System.out.println("Printing from MFD...");
	}

	@Override
	public void scan() {
		System.out.println("Scanning from MFD...");
	}
}

//Benefits:
//Each class only implements the functionality it supports.
//No unnecessary methods or exceptions.
//Easy to extend and maintain.