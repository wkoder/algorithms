#!/bin/bash

compile() {
  rm io/*.class
  javac io/*.java
}

measure_solution() {
  solution=$1
  input_file=$2
  output=$(perf stat -r 100 -B sh -c "java -cp io $solution < $input_file" 2>&1)
  runtime=$(echo "$output" | grep "time elapsed" | sed 's/^ *//g')
  echo "$runtime"
}

measure_file() {
  input_file=$1
  declare -a solutions=("InputReaderSolution" "StringTokenizerSolution" "ScannerSolution")
  echo "Measuring solutions with file $input_file:"
  all_runtimes=
  for solution in "${solutions[@]}"; do
    runtime=$(measure_solution "$solution" "$input_file")
    all_runtimes="  $runtime for $solution
$all_runtimes"
  done
  echo "$all_runtimes" | grep -v "^$" | sort
  echo
}

# Check preconditions
if (( EUID != 0 )); then
  echo "Please run as root (perf requires it)"
  exit
fi

# Compile and measure performance
compile
for input_file in io/files/*.txt; do
  measure_file "$input_file"
done
