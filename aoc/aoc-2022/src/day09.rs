use std::collections::HashSet;
use std::fs;

pub fn a(input_file: &str) -> usize{
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let instructions = input
        .split_terminator('\n')
        .map(|x| {
            let mut x = x.split(' ');

            let dir = x.next().unwrap();
            let count = x.next().unwrap().parse::<i32>().unwrap();

            (dir, count)
        });

    let mut h_coords = (0, 0);
    let mut t_coords = (0, 0);

    let mut visited_coords = HashSet::new();
    for i in instructions {
        let h_vec = match i.0 {
            "U" => (0, 1),
            "R" => (1, 0),
            "D" => (0, -1),
            "L" => (-1, 0),
            _ => unreachable!()
        };

        for _ in 0..((i.1) as usize) {
            // Updating Head Location
            h_coords.0 += h_vec.0;
            h_coords.1 += h_vec.1;

            // Getting vec from T to H
            let diff = (h_coords.0 - t_coords.0, h_coords.1 - t_coords.1);

            // If H exceeds radius of 1 unit from T, must update T location
            if (diff.0 as i32).abs() > 1 || (diff.1 as i32).abs() > 1 {
                t_coords.0 += diff.0 - h_vec.0;
                t_coords.1 += diff.1 - h_vec.1;
            }

            visited_coords.insert(t_coords);
        }
    }

    // answer
    visited_coords.len()
}

pub fn b(input_file: &str) -> usize {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");
    let instructions = input
        .split_terminator('\n')
        .map(|x| {
            let mut x = x.split(' ');

            let dir = x.next().unwrap();
            let count = x.next().unwrap().parse::<i32>().unwrap();

            (dir, count)
        });

    let mut h_coords = (0, 0);              // coords of head knot
    let mut rope_coords = vec![(0, 0); 9];  // coards of tail knots

    let mut visited_coords = HashSet::new();
    for i in instructions {
        // for head movement
        let h_vec = match i.0 {
            "U" => (0, 1),
            "R" => (1, 0),
            "D" => (0, -1),
            "L" => (-1, 0),
            _ => unreachable!()
        };

        for _ in 0..((i.1) as usize) {
            // Updating Head Location
            h_coords.0 += h_vec.0;
            h_coords.1 += h_vec.1;


            let mut lead_coords = h_coords;
            for j in rope_coords.iter_mut() {
                // Getting vec from lead knot to following knot
                let diff = (lead_coords.0 - j.0, lead_coords.1 - j.1);

                // If lead exceeds radius of 1 unit from following, must update follower location
                if (diff.0 as i32).abs() > 1 || (diff.1 as i32).abs() > 1 {
                    // if lead is 2 units away from the follower in
                    //  an axis, the follower must move one unit
                    //  towards the lead in that axis.

                    // if the lead exceeds 2 units of distance from the follower
                    //  in one axis, there is also a change that they are separated
                    //  by one unit in the other axis, which is why I add the
                    //  diff if |diff| neq 2 in that dir.
                    if (diff.0 as i32).abs() == 2 {
                        j.0 += diff.0 / 2;
                    } else {
                        j.0 += diff.0;
                    }

                    if (diff.1 as i32).abs() == 2 {
                        j.1 += diff.1 / 2;
                    } else {
                        j.1 += diff.1;
                    }
                }

                lead_coords = *j;
            }

            visited_coords.insert(rope_coords[8]);
        }
    }

    // answer
    visited_coords.len()
}
