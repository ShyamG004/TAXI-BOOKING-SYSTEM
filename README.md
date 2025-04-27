# Taxi Booking System

A simple Java-based taxi booking simulation that assigns taxis to customer requests based on proximity, availability, and earnings. The program processes ride requests, calculates fares, and tracks each taxi's bookings and earnings.

---

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
  - [Input Format](#input-format)
  - [Sample Input & Output](#sample-input--output)
- [Code Structure](#code-structure)
- [How It Works](#how-it-works)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- Maintains multiple taxis with unique IDs, current positions, and total earnings.
- Selects the best available taxi for each request based on:
  - Proximity to pickup point
  - Taxi availability at requested time
  - Lowest total earnings (for tie-breaking)
  - Lowest taxi ID (final tie-breaker)
- Calculates fare using a base fare plus per-kilometer rate beyond a threshold distance.
- Rejects requests that exceed the maximum allowed distance to pickup.
- Tracks bookings and updates each taxi's position and earnings.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

## Installation

1. Clone or download this repository.
2. Navigate to the project directory.
3. Compile the code:

   ```bash
   javac TaxiBookingSystem.java
   ```

## Usage

Run the program with standard input. You can type input manually or redirect from a file:

```bash
java TaxiBookingSystem < input.txt
```

### Input Format

1. **N** — Number of taxis (integer)
2. **P** — Number of points on the map (integer)
3. **P-1** space-separated integers — Distances between consecutive points
4. **P-1** space-separated integers — Travel times (in minutes) between consecutive points
5. Four integers: **k** (threshold km), **I** (base fare), **X** (per‑km rate), **Y** (max allowed distance)
6. **n** — Number of booking requests (integer)
7. For each request:
   - **customerName** (string)
   - **pickupPoint** (integer)
   - **dropPoint** (integer)
   - **HH:MM** — Pickup time in 24‑hour format

### Sample Input & Output

**Input:**
```
3           # Number of taxis
5           # Number of points
2 3 4 5     # Distances between points
1 1 1 1     # Travel times between points
5 50 10 10  # k, I, X, Y
4           # Number of requests
Alice 1 3 09:00
Bob 2 5 09:10
Carol 3 1 09:20
Dave 4 2 09:30
```

**Output:**
```
Alice Taxi-1 70 09:09
Bob Taxi-2 100 09:13
Carol Taxi-3 60 09:18
Dave REJECTED
```

## Code Structure

- **TaxiBookingSystem.java** — Main class containing `public static void main`.
- **Taxi** — Represents a taxi, tracks its ID, current position, bookings, and earnings.
- **Booking** — Represents a single booking with drop time, drop point, and fare amount.
- Utility methods to calculate map distances and travel times.

## How It Works

1. **Initialization**: Create N `Taxi` objects, each starting at point 1 with zero earnings.
2. **Map Data**: Read distance and time arrays for consecutive points.
3. **Fare Parameters**: Read threshold distance `k`, base fare `I`, per‑km rate `X`, and max allowed distance `Y`.
4. **Request Processing**:
   - For each booking request, parse customer name, pickup/drop points, and time.
   - Iterate over all taxis to find those available at requested time.
   - Select the taxi with the smallest distance to pickup; break ties by earnings and taxi ID.
   - If no taxi is within `Y` km or none available, print `REJECTED`.
   - Otherwise, compute fare, drop time, update taxi state, and print assignment.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.


