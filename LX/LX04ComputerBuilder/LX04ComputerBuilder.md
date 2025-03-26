LX04 ComputerBuilder
==========

Module: `LX/LX04ComputerBuilder`

Package: `ee.taltech.iti0202.computerbuilder`

## Exercise

Develop the EX13ComputerStore further. This exercise doesn't have any automated tests meaning you are free to choose the
implementation yourself.
The grade will be determined on your defense.

**Solutions with less than 90% code coverage won't be accepted!**

In this exercise, you will add a feature to `Store` class to purchase fully built computers in addition to
components.
You will probably need to refactor and change some things in your already existing code (like replace `Component` class
with different subclasses for each component type).

Grading scheme (total 20p):

- Functionality is fully implemented and working
- Used Factory pattern
- Code style with some examples:
    - No copy-pasted code
    - Correct visibility
    - Complicated conditions in if statements are replaced with methods (e.g. `if (isValidComputer())`)
    - Methods should only have a single purpose and try to be max 15 lines long
    - Magic numbers and strings
- Code structure (how well OOP principles are used and code is split into smaller chunks)
- Tests

## New additions

- `Store` now sells prebuilt computers made from components. Computers are only built when a new order is
  received (computers aren't held in some kind of warehouse and therefore aren't stored in the database).
- `Customer` can order a new computer. `Customer` has an overview of all of his/her computers.
- Similar to regular components `Store` also has to make profit when selling computers.
- Your tests will also need to cover your preexisting code from EX13, so we can be sure that the new functionality
  hasn't broken anything from before.
- Think about how to solve the problem when component reference is passed from the store/database to the customer and
  customer can change the amount - then the amount will also change for the store/database. But customer shouldn't be
  able to change store/database state. There are several possible solutions for that, find one.

## Components

Every computer is assembled from parts.

Components needed to build a single PC (only one from each category):

- Processor (CPU)
- Graphics card (GPU)
- Memory (RAM)
- Motherboard
- Storage
    - Either a Hard Disk Drive (HDD), or a Solid State Drive (SSD). SSDs are much faster but cost more.
    - You can ignore storage capacity and only consider speed.
- Power Supply (PSU)
- Case

Laptops also require (only one from each category):

- Keyboard
- Touchpad
- Screen
- Battery

Even though normally laptops are assembled in a factory, we will ignore this fact in this exercise.
For the sake of simplicity, we can also ignore the size of components and every component will be compatible with each
other
(e.g. every CPU will work with every motherboard and large desktop PSU will fit in a laptop).

This isn't directly necessary to read but if it's difficult to comprehend/visualize about how computers look from the
inside and what components they use then [here's link to a guide](http://ati.ttu.ee/IAX0043/Lab1Test/) from one of your
upcoming courses.

## Compatibility

You will need to take into account the power consumption when assembling a computer.
Each component consumes a certain number of watts, and the power supply also has a certain amount of watts it can
deliver.

**The sum of every component must be less or equal to the PSUs maximum capacity.**

If we have the following components (CPU -> 110W, GPU -> 250W, RAM -> 10W, HDD -> 5W) then their total energy usage is
110 + 250 + 10 + 5 = 375 (W). In that case the capacity of the PSU has to be at least 375 W.

## Orders

Clients can order a new computer from `Store` where the order includes:

- *budget* - the maximum amount the client is willing to pay for the computer.
  There's also a possibility that client has no budget amount (no upper bound for spending).
- *use case* - this exercise only has two use cases: workstation and gaming computer.
  Like with budget, it is possible that client has no use case specified.
- *type* - whether the computer is a desktop/tower PC or a laptop.

**The goal of our system is to build the best possible computer for the client based on the order**.
Meaning that the computer is the fastest and its price would not exceed client's budget.

In order to evaluate different computers we need a way to compare their components.
We need to compare the components directly and in a broader sense to compare the entire system. For example:

- How much is processor A faster than processor B?
- How much would a good processor vs a good graphics card increase performance for the whole system?

When comparing different components, you will also need to consider the client's use case where depending on their work
some component's have higher impact on speed.
To simplify matters, in this exercise gaming computers only have bigger importance on graphic cards and workstation
value better processors more.
If use case is missing then the components have equal weight.

## Way to compare components

The way you can measure the speed of different components is by using the `performancePoints` variable already included
in the `Component` class.
For example, good processor will give 200 points to the system but a bad processor only 100. Good GPU gives 300 points,
bad one 100 etc.
The total speed of the computer is the sum of every component's speed.
Some component's that don't affect overall speed like PSU, keyboard etc may have 0 performance points.

For different use cases we can just multiply the performance points of the prioritized component by some coefficient
like 1.3x.

When choosing the best possible computer you can just find all the valid combinations and then find the one with the
most performance points.

## Factory pattern

You will need create a separate class to assemble the computers where you need to use factory method design pattern.
In essence, when `Store` class needs a new `Computer` object, it doesn't access the constructor of the class
directly.
Instead, it will use a separate method in a different factory class that depending on the arguments will return the
desired object.

The point of this design pattern is that all the complex logic required to create an object can be kept in a separate
place and make code more loosely coupled.

You can read more about this pattern
from [here](https://howtodoinjava.com/design-patterns/creational/implementing-factory-design-pattern-in-java/).

A tiny example how it might look like in this exercise to give you a rough idea. `Computer` is an abstract class or an
interface and `useCase` and `type` parameters are enums.

```
public Computer assembleComputer(double budget, UseCase useCase, Type type) {
    if (type.equals(Type.Desktop)) {
        return new DesktopComputer();
    } else if (type.equals(Type.Laptop)) {
        return new LaptopComputer();
    }
}
```
