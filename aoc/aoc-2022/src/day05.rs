pub fn a () {
    let mut input = include_str!("../inputs/day05.txt").split("\n\n");

    let mut top = input
        .next()
        .unwrap()
        .rsplit_terminator('\n');

    // getting # of stacks
    let stack_count = top.next()
                         .unwrap()
                         .split_whitespace()
                         .last()
                         .unwrap()
                         .parse::<i32>()
                         .unwrap();

    // init vector of stacks
    let mut stacks: Vec<Vec<String>> = vec![Vec::new(); stack_count as usize];

    // populating stacks
    top.for_each(|x| {
        for i in 0..stack_count {
            let curr_stack = stacks.get_mut(i as usize).unwrap();
            let item = x.get((i * 4 + 1) as usize..(i * 4 + 2) as usize).unwrap().trim().to_string();

            if !item.is_empty() {
                (*curr_stack).push(item);
            }
        }
    });

    // working through the instructions
    input.next()
        .unwrap()
        .split_terminator('\n')
        .for_each(|line| {
            let mut line_split = line.split_whitespace();

            line_split.next();
            let amt = line_split.next().unwrap().parse::<i32>().unwrap();

            line_split.next();
            let from = line_split.next().unwrap().parse::<i32>().unwrap() - 1;

            line_split.next();
            let to = line_split.next().unwrap().parse::<i32>().unwrap() - 1;

            let mut i = 0;
            while i < amt {
                let item = stacks.get_mut(from as usize)
                                 .unwrap()
                                 .pop()
                                 .unwrap();

                stacks.get_mut(to as usize)
                      .unwrap()
                      .push(item);

                i += 1;
            }
        });

    // honestly don't know exactly what "to_owned()" is for, just slapped it in there
    let output = stacks.iter().fold("".to_owned(), |acc, x| {
        acc + x.last().unwrap()
    });

    println!("day05 a: {output}");
}

pub fn b () {
    let mut input = include_str!("../inputs/day05.txt").split("\n\n");

    let mut top = input
        .next()
        .unwrap()
        .rsplit_terminator('\n');

    // getting # of stacks
    let stack_count = top.next()
                         .unwrap()
                         .split_whitespace()
                         .last()
                         .unwrap()
                         .parse::<i32>()
                         .unwrap();

    // init vector of stacks
    let mut stacks: Vec<Vec<String>> = vec![Vec::new(); stack_count as usize];

    // populating stacks
    top.for_each(|x| {
        for i in 0..stack_count {
            let curr_stack = stacks.get_mut(i as usize).unwrap();
            let item = x.get((i * 4 + 1) as usize..(i * 4 + 2) as usize).unwrap().trim().to_string();

            if !item.is_empty() {
                (*curr_stack).push(item);
            }
        }
    });

    let mut temp_stack: Vec<String> = Vec::new();
    // working through the instructions
    input.next()
        .unwrap()
        .split_terminator('\n')
        .for_each(|line| {
            let mut line_split = line.split_whitespace();

            line_split.next();
            let amt = line_split.next().unwrap().parse::<i32>().unwrap();

            line_split.next();
            let from = line_split.next().unwrap().parse::<i32>().unwrap() - 1;

            line_split.next();
            let to = line_split.next().unwrap().parse::<i32>().unwrap() - 1;

            // loading the top n items into a temp stack
            let mut i = 0;
            while i < amt {
                let item = stacks.get_mut(from as usize)
                                 .unwrap()
                                 .pop()
                                 .unwrap();

                temp_stack.push(item);

                i += 1;
            }

            // loading the items in the temp stack into the stack accumulator
            // there is probably a better way to do this
            let mut i = 0;
            while i < amt {
                let item = temp_stack.pop().unwrap();

                stacks.get_mut(to as usize)
                      .unwrap()
                      .push(item);

                i += 1;
            }
        });

    // honestly don't know exactly what "to_owned()" is for, just slapped it in there
    let output = stacks.iter().fold("".to_owned(), |acc, x| {
        acc + x.last().unwrap()
    });

    println!("day05 b: {output}");
}
