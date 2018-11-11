# TODO List

## Colisão
- [ ] Colisão com as boundaries do mapa
    - [ ] Mapa limitado?
    - [ ] Gerar novo "chunk"?
- [ ] Tratar melhor colisão de UI
    - [ ] QuadTree só para UI?

## Movimento
- [ ] Centralizar tela no personagem
    - [ ] Tela andar somente com o personagem

## Assets
- [ ] Novos blocos
- [ ] Ferramentas de som
- [ ] MinedImage dos Blocos
- [ ] Imagem de fundo

## Renderização
- [ ] Animação estática entre Drops?

## Geral código

## Outro

# Finalizados

## Colisão
- [X] Conferir alternativas sistema de colisão 
    > - [ ] S.A.T (Por enquanto somente quadrados)
    - [X] QuadTree
    > - [ ] Reimplementar QuadTree
    - [X] Remover Box2D

## Movimento

## Assets

## Renderização
- [X] Escurecer blocos não-visitados
- [X] Método de animação mais organizado
    > - [ ] Superclasse
    > - [ ] Interface
    - [X] Composição
- [X] Criar versão melhorada da interface Renderable
    - [X] Superclasse com x, y, width e height?

## Geral código
- [X] Planejamento de inventário
- [X] Refatorar para utilizar esquema push-pop transform
- [X] Criar classe Slot
    - [X] Categorizar slots com herança de tipos de item?

## Outro
- [X] Subir pro Git
- [X] Passar essa lista para MarkDown