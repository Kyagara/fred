package net.kyagara.fred.config;

import java.util.List;

import eu.midnightdust.lib.config.MidnightConfig;

public class FredConfig extends MidnightConfig {
        @Entry(name = "8Ball answers")
        public static List<String> magicBallAnswersList = List.of(
                        "It is certain.",
                        "It is decidedly so.",
                        "Without a doubt.",
                        "Yes definitely.",
                        "As I see it, yes.",
                        "Most likely.",
                        "Yes.",
                        "Reply hazy, try again.",
                        "Ask again later.",
                        "I'm gonna leave you with that one.",
                        "Better not tell you now.",
                        "Cannot predict now.",
                        "I guess so?",
                        "Concentrate and ask again.",
                        "Don't count on it.",
                        "Oh hell no.",
                        "lmao no.",
                        "No.",
                        "My reply is no.",
                        "My sources say no.",
                        "Very doubtful.");

        @Entry(name = "Quotes list")
        public static List<String> quotesList = List.of(
                        "MACHINE! TRANS. RIGHTS. NOW.",
                        "How can this be? Bested by this - this FUCKING GOPRO",
                        "Machine, I'm dying. Please stop beatboxing.",
                        "B-But council, the machine said a curse word in my CHRISTIAN MINECRAFT SERVER!",
                        "gabriel gaming",
                        "YOU ARE POGGERS. YOU ARE POGCHAMP. YOU ARE NOT CRINGE.",
                        "MACHINE. I'M GOING TO POST CRINGE IN GENERAL.",
                        "IMPOSSIBLE. HERSHEY. UNSPEAKABLE, HERSHEY. HERSHEY.",
                        "MAY YOUR VOWS BE MANY, AND YOUR DAYS FEW.");
}