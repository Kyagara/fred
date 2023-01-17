# Fred

Mod developed for a private modpack for some friends. I don't plan on doing backports to any version for now.

The main goal with this mod is to add some quality of life features that would normally need another mod to be installed, things we find
funny as items/commands and to practice some java along the way.

- The name was the first thing a friend said after I asked for a project name.
- The mod icon is my friend's doggo :), its temporary, no idea of what to actually use.

> Most features are configurable.

> Check the most recent Github Action artifact for the mod jar.

## Features

- Custom window title.
- Movement with screens open (inventory, crafting table and a lot more).
- Auto walk.
- Zoom based on [VanillaZoom](<https://github.com/ricksouth/serilum-mc-mods/tree/master/sources-fabric/Vanilla%20Zoom%20(Fabric)>) and
  changing zoom distance.
- Link items in chat based on [Show Me What You Got](https://github.com/apace100/show-me-what-you-got).
- Sound on chat message.
- More control over the Minecraft Music (delay between songs, skip, change volume, show what is playing).
- A music player screen based on [Music Control](https://github.com/sf-inc/music_control). (Initial implementation, needs a search function)
- Disable any Toasts (tutorial, recipe, advancement).
- Keep the current session chat history.
- Show basic world info on chat when joining a world.
- Use XP to create Experience bottle.
- Total blocks broken and placed statistic.

## New recipes

- Name Tag
- Rotten Flesh to Leather
- Horse Armor (diamond, gold, iron)
- Saddle

## Keybinds

- **Up Arrow** - Increases music volume.
- **Down Arrow** - Decreases music volume.
- **Left Arrow** - Music player screen.
- **Right Arrow** - Skip music.
- **Right Control** - Shows the name of the current music playing.
- **Left Alt** - Press while hovering an item to link it in chat.
- **C** - Zoom (conflicts with a creative mode keybind, you can change this).
- **G** - Auto walk.

## Commands

> Server side commands that broadcast() to the server.

- **roll** - Random number from 1 to the number provided as argument.
- **8ball** - It can answer your every question, as long as your question can be answered with yes or no.
- **quotes** - Gives a random quote from a config file, defaults to some random stuff about ULTRAKILL for the lack of a better idea for
  default quotes.

> Client side commands.

- **shrug** - Shrugs.
- **flip** - Flips a table.
- **unflip** - Unflips said table.

## Joke features

> All items/blocks have recipes, have their own statistics and can be disabled.

- (Item) **Trumpet** - Doot until it is done. Repaired with gold nuggets.
- (Item) **The Rock Block** - Him.
- (Item) **Rei Fumo Block** - Tumbling down.
- (Keybind) **Period** - Plays My Movie SFX.

## Setup

For setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are
using.

## License

Fred is licensed under the MIT license.

This project contains code
from [trumpet-skeleton-fabric](https://github.com/JamiesWhiteShirt/trumpet-skeleton-fabric/), [show-me-what-you-got](https://github.com/apace100/show-me-what-you-got), [VanillaZoom](<https://github.com/ricksouth/serilum-mc-mods/tree/master/sources-fabric/Vanilla%20Zoom%20(Fabric)>)
and is based on [Kaupenjoe's Fabric Tutorial](https://www.youtube.com/playlist?list=PLKGarocXCE1EeLZggaXPJaARxnAbUD8Y_).
