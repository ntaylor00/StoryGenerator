## Challenges & Fixes:
1. Credentials Issues
    - We had some issues with the API key not being found / recognized. The solution was to add the API Key environment variable to the run configurations in IntelliJ.
    - AI assistance solved this issue
2. No Output
   - There was a point in time where openAI was sucessfully generating responses based on prompts, but it was not printing to the proper textarea.
   - AI assistance solved this issue
3. Image fitting:
   - The display window would often cut-off images and the exit button from sight of the user.
   - A lot of trial, error, adjusting numbers, and then realizing we had to adjust the size of not only the base window but the base image as well, while maintaining aspect ratio.
4. Image generation format
   - Image format reqeusts/prompts were confusing to structure/implement into the genImage method, even with generateStory as an example
   - AI assistance and a little trial and error solved this issue.
5. Can't use e -> {} when already defining e ->{} in javaFX
   - Clicking image generation would cause a seperate window with your generated image to pop up. This pop-up-window had an exit button for users to click, but unforetunately when defining the setOnAction within a setOnAction, JavaFX doesn't allow you to define e twice.
   - I had to implement the pop-up as a seperate method within StoryGeneratorApp.java to not have e defined twice within the same scope, but still alow for the pop-up-window to have functioning buttons

## Design Pattern Justification:
The pattern is simple: Gather user input, use user intput to generate a request, collect results of that request, and output the results to the user. This can be utalized for just about any API for any purpose, whether it's an image generator, music reccomendor, or programming assistant. Foretunately we don't have to concern ourselves too heavily about how or why the API methods/objects do what they do, our only concern is how to prompt it and how to gather the information correctly. I belive the generation/construction of requests would be considered a "factory" feature.

## OOP Four Pillars
1. Inheritance: There are not many uses of inheritance within our program. Mostly code-reuse, though it is possible methods such as generateStory and genImage could have utalized inheritance
2. Polymorphism: Without much inheritance, there's not much room for polymorphism as well. Methods relating to the TextAdventure method would be good for this, as it is essentially a variation of the generateStory method with altered paremeters/streaming. 
3. Encapsulation: Private feilds such as Client and api_url keep such high risk variables from being directly accessed, instead simply modified/gathered by the various methods within APIClient.java. Environment variables are utalized to ensure the API key used for development is not tampered with, accessed, or posted on github.
4. Abstraction: The user only interacts with our program through a graphical interface, none of which offer any insight into our implementation or operations. Further, our GUI code is seperate from our source functions so that one could swap it for a different GUI if needed, all it would require is re-wiring the buttons.

## AI Usage:
- Credentials issues:
    - Asked: Uploaded code snippets and asked about the cause of the credentials-related error messages and how to fix them.
    - Modifications: Add environment variable with API key to the run configurations.
    - Verification: Lack of credentials error message after modifications.
- Issues with lack of output:
    - Asked: Uploaded code snippets and asked why nothing was being printed (credentials issues had been solved at this point).
    - Modifications: Modified the way the story was printed. The generateStory function in APIClient.java was returning an object, and the solution was to extract components of the object and add them to a string to be printed.
    - Verification: Visual - the story was printing successfully.
  - Image generation syntax:
      - Asked: How to properly generate/format an image generation request for openAPI given a snippet of code
      - Modifications: copy and pasted provided code as necessary
      - Verified: Images generated sucessfully
  - Junit Testing:
    - Asked: To come up with 10 Junit tests given all of the functions in our program
    - Modifications: Copy and pasted all Junit tests provided
    - Verified: Tests passed sucessfully 
