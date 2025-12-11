# Story Generator

## Setup:
1. Get API key from https://platform.openai.com/docs/overview
2. export OPENAI_API_KEY="your-key"   # or set in config.properties
3. You may need to input "--module-path "C:\Program Files\Java\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.fxml" in the congifgurations VM options, depending on how/where your JavaFX files are.
4. Run StoryGeneratorApp.java

## Features
- [x] Story generation given a user-entered prompt, genre, and story length.
- [x] Default prompt, genre, and/or story length if left blank
- [x] Option to regenerate / generate new story
- [x] Clear / start over
- [x] Text Adventure mode

Bonus Feature:
- [x] Optional Image Generation

(Work In Progress):
- [ ] Edit generated story
- [ ] More robust memory retention

## Design Patterns
- Strategy: Different generation modes
    - User entered genre, prompt, story length
    - Default genre, prompt, and/or story length
    - Text adventure mode
- Factory: Request creation
- Observer: UI updates on async completion
- Singleton: APIClient / Config

## Demo
[<https://youtu.be/CYV8oopSt14>]
