import OpenAI from "openai";
        const client = new OpenAI();

tools = [
    {
        "type": "function",
        "name": "generate_story",
        "description": "Generate a short story given a genre and other user specifications.",
        "parameters": {
            "type": "object",
            "properties": {
                "genre": {
                    "type": "string",
                    "description": "A story genre like science-fiction or comedy",
                },
                "main_character": {
                    "type": "string",
                    "description": "A character description that will serve as the protagonist of the story",
                },
            },
            "required": ["genre"],
            "required": ["main_character"],
            //and optional photo generation
        },
    },
]

def generate_story(genre, main_character):
    user_input = [
        {"genre": genre, "main_character": main_character}
    ]

response = client.responses.create(
    model="gpt-5-nano",
    tools-tools,
    input=user_input,
)
/*const response = await client.response.create({
    model: "gpt-5-nano",

    prompt: {
        id: "prmpt_abc123",
        variables: {
            genre: "Sci-fi",
            length: "2 paragraphs",
            generate_image: "No" // extra
        }
    }
    //input: "" -- take from user input
});*/

console.log(response.output_text);