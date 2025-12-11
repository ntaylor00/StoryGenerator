# Story Generator

## Setup:
1. Get API key from https://platform.openai.com/docs/overview
2. export OPENAI_API_KEY="your-key"   # or set in config.properties
3. Run StoryGeneratorApp.java

## Features
- [x] Story generation given a user-entered prompt, genre, and story length.
- [x] Default prompt, genre, and/or story length if left blank
- [x] Option to regenerate / generate new story
- [X] Clear / start over
- [x] Text Adventure mode

(Work In Progress):
- [ ] Edit generated story

## Design Patterns
- Strategy: Different generation modes
    - User entered genre, prompt, story length
    - Default genre, prompt, and/or story length
    - Text adventure mode
- Factory: Request creation
- Observer: UI updates on async completion
- Singleton: APIClient / Config

## Demo
[Video link]
