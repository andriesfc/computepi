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

Options:
  --all                 Print out intermediate calculated values of PI.
  --sampleSize INT      Number of points to evaluate per computation of a
                        single value. (default: 100000)
  -i, --iterations INT  How many iterations should the computation be repeated
                        until the final value is reported. (default: 100)
  -h, --help            Show this message and exit

```

