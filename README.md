This forge mod causes monsters that are preventing a player from being able to use a bed to be highlighted for several seconds when the player attempts to use a bed and fails due to the presence of those monsters.

This mod is completely serversided; it does not need to be installed on the client side to function unless the user desires to use this mod in singleplayer or in a LAN world they are hosting.

Built jars are available here https://www.curseforge.com/minecraft/mc-mods/cant-sleep-clowns-will-eat-me

This mod is configurable; the default config below is generated at `<save-folder>/serverconfig/cant-sleep-clowns-will-eat-me-server.toml` when a new world is created.

```toml
#Whether highlighting of sleep-preventing monsters is enabled. Set this to false to disable the feature.
highlight_mobs = true
#How long the highlight effect will last, in ticks.
highlight_duration = 60

```
