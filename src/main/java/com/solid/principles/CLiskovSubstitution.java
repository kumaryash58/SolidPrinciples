package com.solid.principles;

public class CLiskovSubstitution {

    public static void main(String[] args) {
        // Without LSP
        System.out.println("Without LSP:");
        Bird parrot = new Parrot();
        parrot.fly();
        Bird ostrich = new Ostrich();
        try {
            ostrich.fly();
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // With LSP
        System.out.println("\nWith LSP:");
        BirdLSP parrotLSP = new ParrotLSP();
        parrotLSP.eat();
        if (parrotLSP instanceof FlyingBirdLSP) {
            ((FlyingBirdLSP) parrotLSP).fly();
        }

        BirdLSP ostrichLSP = new OstrichLSP();
        ostrichLSP.eat();
        // No fly() method for OstrichLSP, so no risk of exception
    }
}

//============Without LSP- Violating LSP
class Bird {
	public void fly() {
		System.out.println("Bird is flying");
	}
}

class Parrot extends Bird {
	@Override
	public void fly() {
		System.out.println("Parrot is flying");
	}
}

class Ostrich extends Bird {
	@Override
	public void fly() {
		throw new UnsupportedOperationException("Ostrich can't fly");
	}
}

// Problem:
//	 Ostrich is a Bird, but calling fly() on it throws an exception.
//	 This violates LSP because Ostrich cannot be substituted for Bird safely.

//============With LSP- Following LSP
class BirdLSP {
	public void eat() {
		System.out.println("Bird is eating");
	}
}

abstract class FlyingBirdLSP extends BirdLSP {
	public abstract void fly();
}

class ParrotLSP extends FlyingBirdLSP {
	@Override
	public void fly() {
		System.out.println("Parrot is flying");
	}
}

class OstrichLSP extends BirdLSP {
	// Ostrich doesn't fly, so no fly() method
}

//✅ Benefits:
//Parrot can be used wherever FlyingBird is expected.
//Ostrich can be used wherever Bird is expected.
//No unexpected behavior or exceptions — LSP is preserved.
