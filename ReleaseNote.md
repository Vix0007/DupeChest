# Release Notes
All notable changes to the **DupeChest** project will be documented in this file.

## [1.2.4] - 2026-02-04
### Added
- **Shapeless Recipe**: Implemented a high-tier crafting recipe using 9 unique end-game items (Dragon Egg, Mace, Silence Trim, etc.).
- **Creative Menu Integration**: Added the Dupe Chest to the `Functional Blocks` vanilla creative tab.
- **Green Recipe Book Support**: Added recipe unlocking logic so players see the recipe after obtaining a Nether Star or Dragon Egg.

### Fixed
- **Loot Table Fix**: Added a Block Loot Table provider so the Dupe Chest now drops itself as an item when broken in Survival.
- **Advancement Datagen**: Fixed the `cannot find symbol .save()` error by migrating to the 1.21.1 `.build()` and `consumer.accept()` method.

## [1.1.0] - 2026-02-01
### Added
- **Initial Datagen**: Set up the `FabricAdvancementProvider` and created the "Breaking the Matrix" advancement.
- **Core Mechanics**: Completed the Block Entity logic for the duplication chest.

## [1.0.0] - 2026-01-24
### Added
- **Project Start**: Initialized the mod template for Minecraft 1.21.1.
- **Block Registration**: Registered the `DUPE_CHEST_BLOCK` and basic textures.