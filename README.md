# Clifton Strengths Comparator
![Version 0.0.1 Badge][version-badge]

[version-badge]: https://img.shields.io/badge/version-0.0.1-teal.svg

# What is Clifton Strengths?

Gallup® Clifton Strengths is somewhat of a personality test that is commonly used in a professional work environment for the purposes of best understanding the strengths of individuals on a team and assessing where their strengths can be best utilized in an organization.

https://www.gallup.com/cliftonstrengths/en/252137/home.aspx

## The Strengths

Here are the Strengths for which this service will assess an individual. For more information, I highly suggest purchasing your own copy of Clifton Strengths [here](https://store.gallup.com/h/en-us/), or examining the individual strengths on their website [here](https://www.gallup.com/cliftonstrengths/en/253715/34-cliftonstrengths-themes.aspx).

1. Achiever
2. Activator
3. Adaptability
4. Analytical
5. Arranger
6. Belief
7. Command
8. Communication
9. Competition
10. Connectedness
11. Consistency
12. Context
13. Deliberative
14. Developer
15. Discipline
16. Empathy
17. Focus
18. Futuristic
19. Harmony
20. Ideation
21. Includer
22. Individualization
23. Input
24. Intellection
25. Learner
26. Maximizer
27. Positivity
28. Relator
29. Responsibility
30. Restorative
31. Self-Assurance
32. Significance
33. Strategic
34. Woo

# Why this "Comparator"

Simply put, I took this assessment in 2018 and once again in 2024 after living through some of the most extreme life-changing events known to a first-world country (_I've been through a lot, but I would never compare my circumstances to those around the world living in **objectively** worse situations_).

I wanted to see how each individual category shifted after both assessments.

## How it works

Create a text file of an ordered list of your 34 strengths after taking the assessment.

For example:

```text
20180205
Achiever
Woo
Communication   
Learner
Activator
         . . .
```

Then, feed them into the program like so:

```shell
java -jar clstcompare.jar -i results1.txt results2.txt -o result_comparison_20241023.txt
``` 

The `result_comparison_20241023.txt` produced from the above example contains a list of a user's strengths ordered by the latest results, and a signed integer identifying its movement between the assessments:

```text
-- 20241023 --

 1. Woo                 +1
 2. Belief              +26
 3. Communication       +0
 4. Achiever            -3
 5. Context             +11
 
         . . .

-- Sorted by movement --

 1. Belief             +26
 2. Restorative        +26
 3. Harmony            +23
 4. Empathy            +18
 5. Responsibility     +13

```


### Future Improvement Ideas

* Allow for multiple assessments to create a timeline of movement among the strengths.
* HTML output for a prettier, interactive result for a user to navigate.

---

Enjoy!
