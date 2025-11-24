import OpenAI from "openai";
const client = new OpenAI();

const response = await client.response.create({
    model: "gpt-5-nano",
    //input: "" -- take from user input
});