# Titanic
A QOL client for Legacy Minecraft Versions.

## Which versions will this client support?
The client will support Beta 1.1_02 and Beta 1.7.3.

## Is this client bannable on servers?
No, we are in contact with server owners and plan to satisfy all rules.

Titanic will come with a Bukkit API that will allow servers to stop the use of certain features on the server.

## Roadmap
- [x] General designing process
- [x] Create a base for simple features, will prob use a modified version of [Settings](https://github.com/Noxiuam/Settings)
- [ ] Create a way to detect JBanned users, notify them if they are banned
- [ ] Create the UI framework with different paths for each menu (it will work similarly to how endpoints work in express servers)
- [ ] Implementing quality of life features such as a brightness slider & fixes for crashes
- [x] Implement a basic cosmetic system for capes only, wings and emotes will come at a later time
- [ ] Bukkit API to allow servers to disable certain features on the server

### Bug Fixes Roadmap
- [ ] Fix chunk crash when loading them too fast
- [ ] Fix chest inventory "chest" text not being capitalized properly
- [ ] Fix armor models not moving with player swinging animation
- [x] Fix capes models when sneaking and other stuff
- [x] Fix player head model when sneaking and unsneaking
- [x] Remove "Saving level..." when on a server, it doesn't save anything
