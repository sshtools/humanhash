# humanhash
Implements a Java version of the Github [zacharyvoase/humanhash](
https://github.com/zacharyvoase/humanhash) project.


# Example

```
MessageDigest digest = MessageDigest.getInstance("MD5");
digest.update("Some unique content you have".getBytes("UTF-8"));
String humanString = new HumanHashGenerator(digest.digest()).build();
```

This generates the output `twenty-island-east-sodium` consistently with each invocation of the generator.

# Caveats

Don't store the humanhash output, as its statistical uniqueness is only around 1 in 4.3 billion. Its intended use is as a human-readable (and, most importantly, memorable) representation of a longer digest, unique enough for display in a user interface, where a user may need to remember or verbally communicate the identity of a hash, without having to remember a 40-character hexadecimal sequence. Nevertheless, you should keep original digests around, then pass them through the generator only as you're displaying them.

# How It Works

The procedure for generating a humanhash involves compressing the input to a fixed length (default: 4 bytes), then mapping each of these bytes to a word in a pre-defined wordlist (a default wordlist is supplied with the library). This algorithm is consistent, so the same input, given the same wordlist, will always give the same output. You can also use your own wordlist, and specify a different number of words for output.
