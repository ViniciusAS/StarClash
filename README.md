#StarClash

[Link do projeto no GitHub](https://github.com/ViniciusAS/StarClash/)

### Especificações
- Jogo de nave multiplayer
- Utilização da biblioteca [LWJGL 3](https://www.lwjgl.org/) para desenho do jogo utilizando OpenGL

### Padrões de projeto utilizados

- AbstractFactory: modos de jogo: tipos de multiplayer
- AbstractFactory: criação de naves
- Decorator: alteração de propriedades comuns entre naves
- Adaptor: desenho na tela
- Composite: possivel utilização em tiro especial
