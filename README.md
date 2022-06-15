# Simple Command Line App to compute PI using Monte Carlo approximation

## How to build

Execute the following command: 

```shell
./gradlew installDist
```

## How to run

```shell
./build/install/computepi/bin/computepi
```

The following options are available: 

```text
Usage: computepi [OPTIONS]

  Compute PI using Monte Carlo approximation

  For a detail explanation see the following articles:
  https://www.geeksforgeeks.org/estimating-value-pi-using-monte-carlo/
  https://betterprogramming.pub/calculating-pi-%CF%80-with-monte-carlo-using-parallel-computing-with-openmp-and-c-2b3a357f0f78
  https://www.cantorsparadise.com/estimating-%CF%80-using-monte-carlo-simulations-3459a84b5ef9

Options:
  --all                 Print out intermediate calculated values of PI.
  --sampleSize INT      Number of points to evaluate per computation of a
                        single value. (default: 100000)
  -i, --iterations INT  How many iterations should the computation be repeated
                        until the final value is reported. (default: 100)
  -h, --help            Show this message and exit
```

