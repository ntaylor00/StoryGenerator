## Challenges & Fixes:
1. Credentials Issues - We had some issues with the API key not being found / recognized. The solution was to add the API Key environment variable to the run configurations in IntelliJ.
3. No Output - 

## Design Pattern Justification:
(why each pattern fits)

## OOP Four Pillars
(where & why in your codebase)

## AI Usage:
- Credentials issues:
    - Asked: Uploaded code snippets and asked about the cause of the credentials-related error messages and how to fix them.
    - Modifications: Add environment variable with API key to the run configurations.
    - Verification: Lack of credentials error message after modifications.
- Issues with lack of output:
    - Asked: Uploaded code snippets and asked why nothing was being printed (credentials issues had been solved at this point).
    - Modifications: Modified the way the story was printed. The generateStory function in APIClient.java was returning an object, and the solution was to extract components of the object and add them to a string to be printed.
    - Verification: Visual - the story was printing successfully.
