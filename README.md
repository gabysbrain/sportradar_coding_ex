# Scoreboard

This is a simple scoreboard library to keep track of matches.

## Example of usage

```
Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());

scoreboard.startMatch("Mexico", "Canada");
scoreboard.startMatch("Spain", "Brazil");
scoreboard.updateScore("Mexico", "Canada", 0, 5);
scoreboard.updateScore("Spain", "Brazil", 10, 2);
scoreboard.finishMatch("Mexico". "Canada");

scoreboard.getScores();
```

## Contributing

### Setup

This project uses the [gradle](https://gradle.org/) build tool. 
The most important tasks are:

* `gradle build`
* `gradle test`
* `gradle check`

### Code Quality & Style

This project uses [spotless](https://github.com/diffplug/spotless) to ensure
proper code style and consistency. It is configured to adhere to the 
[Google style guide](https://google.github.io/styleguide/javaguide.html).

This project uses [pre-commit](https://pre-commit.com/) to ensure that formatting
guidelines are met. With a fresh checkout run: `pre-commit install` to install
the hooks.

### Commit Style

Commits should be small and only handle a single concern.
They should state the _what_ and the _why_ of the changes.
A commit message has a header and an optional body.
Please make sure the header is not too long (~80 chars max)

Every commit has a type and a scope:
```
<type>(<scope>): <short summary>
 |       |            | What and why, if not enough space, use the body
 |       | part of the project that is being changed
 | something like "test", "fix", "refactor", "chore", "docs", etc.
```



