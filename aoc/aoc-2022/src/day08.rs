use std::fs;

pub fn a (input_file: &str) -> i32 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    // reading in the grid
    let in_grid : Vec<Vec<i32>> = input.split_terminator('\n')
                                    .map(|x| {
                                        x.chars()
                                         .map(|y| y.to_digit(10).unwrap() as i32)
                                         .collect::<Vec<i32>>()
                                    })
                                    .collect();

    let cols = in_grid.len();
    let rows = in_grid.get(0).unwrap().len();

    let mut out_grid = vec![vec![false; rows]; cols];

    // Right
    for i in 0..rows {
        let mut last_height = -1;
        for j in 0..cols {
            if in_grid[j][i] > last_height {
                last_height = in_grid[j][i];
                out_grid[j][i] = true;
            }
        }
    }

    // Left
    for i in 0..rows {
        let mut last_height = -1;
        for j in (0..cols).rev() {
            if in_grid[j][i] > last_height {
                last_height = in_grid[j][i];
                out_grid[j][i] = true;
            }
        }
    }

    // Down
    for i in 0..cols {
        let mut last_height = -1;
        for j in 0..rows {
            if in_grid[i][j] > last_height {
                last_height = in_grid[i][j];
                out_grid[i][j] = true;
            }
        }
    }

    // Up
    for i in 0..cols {
        let mut last_height = -1;
        for j in (0..rows).rev() {
            if in_grid[i][j] > last_height {
                last_height = in_grid[i][j];
                out_grid[i][j] = true;
            }
        }
    }

    // getting # of trees
    let mut output = 0;
    for i in 0..cols {
        for j in 0..rows {
            if out_grid[i][j] {
                output += 1;
            }
        }
    }

    output
}

pub fn b (input_file: &str) -> i32 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    // reading in the grid
    let in_grid : Vec<Vec<i32>> = input.split_terminator('\n')
                                    .map(|x| {
                                        x.chars()
                                         .map(|y| y.to_digit(10).unwrap() as i32)
                                         .collect::<Vec<i32>>()
                                    })
                                    .collect();

    let cols = in_grid.len();
    let rows = in_grid.get(0).unwrap().len();

    let mut output = 0; // scenic score
    // looping through each tree in the grid
    for i in 0..cols {
        for j in 0..rows {
            let curr_tree_height = in_grid[i][j];
            let mut mul = 1;

            // Right
            let mut sum = 0;
            for x in i..cols {
                if x != i {
                    sum += 1;

                    if in_grid[x][j] >= curr_tree_height {
                        break;
                    }
                }
            }
            mul *= sum;

            // Left
            sum = 0;
            for x in (0..i).rev() {
                if x != i {

                    sum += 1;
                    if in_grid[x][j] >= curr_tree_height {
                        break;
                    }
                }
            }
            mul *= sum;

            // Down
            sum = 0;
            for x in j..rows {
                if x != j {
                    sum += 1;

                    if in_grid[i][x] >= curr_tree_height {
                        break;
                    }
                }
            }
            mul *= sum;

            // Up
            sum = 0;
            for x in (0..j).rev() {
                if x != j {
                    sum += 1;

                    if in_grid[i][x] >= curr_tree_height {
                        break;
                    }
                }
            }
            mul *= sum;

            if mul > output {output = mul;}
        }
    }

    output
}
