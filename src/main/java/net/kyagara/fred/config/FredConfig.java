package net.kyagara.fred.config;

import java.util.List;
import eu.midnightdust.lib.config.MidnightConfig;

public class FredConfig extends MidnightConfig {
        @Comment(centered = true)
        public static Comment miscCategory;

        @Entry(name = "Enable inventory movement")
        public static boolean inventoryMovement = true;

        @Entry(name = "Get basic world info on join")
        public static boolean joinMessage = true;

        @Entry(name = "Clear chat on leave")
        public static boolean clearChat = false;

        @Entry(name = "Disable all advancement toasts")
        public static boolean disableAdvancementToasts = false;

        @Entry(name = "Disable all recipe toasts")
        public static boolean disableRecipeToasts = true;

        @Entry(name = "Disable all tutorial toasts")
        public static boolean disableTutorialToasts = true;

        @Entry(name = "Disable all system toasts")
        public static boolean disableSystemToasts = true;

        @Comment(centered = true)
        public static Comment itemCategory;

        @Entry(name = "Enable The Rock Block")
        public static boolean enableTheRockBlock = true;

        @Entry(name = "Enable Trumpet")
        public static boolean enableTrumpet = true;

        @Comment(centered = true)
        public static Comment audioCategory;

        @Entry(name = "Enable chat message sound")
        public static boolean chatMessageSound = true;

        @Entry(name = "Player message volume")
        public static float chatMessageVolume = 0.15F;

        @Entry(name = "Player message pitch")
        public static float chatMessagePitch = 3.0F;

        @Entry(name = "Music mininum delay")
        public static int musicMinDelay = 7000;

        @Entry(name = "Music maximum delay")
        public static int musicMaxDelay = 10000;

        @Comment(centered = true)
        public static Comment commandCategory;

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