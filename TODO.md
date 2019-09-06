# TODO List

## Colisão
- [x] Colisão com as boundaries do mapa
    - [-] Mapa limitado?
    - [x] Gerar novo "chunk"?
- [ ] Tratar melhor colisão de UI
    - [ ] QuadTree só para UI?

## Movimento
- [X] Centralizar tela no personagem
    - [X] Tela andar somente com o personagem
- [ ] Implementar velocidade de movimento

## Assets
- [ ] Novos blocos
- [ ] Ferramentas de som
- [ ] MinedImage dos Blocos
- [X] Imagem de fundo
    - [ ] Day/Night cicle

## Inventario
- [ ] Slots de equipamento
- [ ] Tamanho variável baseado em mochilas?
- [ ] Items com peso?
- [ ] Implementar divisão de blocos onMouseDragged

## Renderização
- [ ] Animação estática entre Drops?
- [x] Renderização independente de elementos UI

## Geral código
- [x] Sistema leitura de blocos em Chunks

## Outro
- [x] Corrigir dimensões verticais do mapa
- [x] Organizar Sizes.WORLD_HEIGHT
- [ ] Centralizar localização de constantes

# Finalizados

## Colisão
- [X] Conferir alternativas sistema de colisão 
    > - [ ] S.A.T (Por enquanto somente quadrados)
    - [X] QuadTree
    > - [ ] Reimplementar QuadTree
    - [X] Remover Box2D

## Movimento

## Assets

## Inventario
- [X] Planejamento de inventário
- [X] Criar classe Slot
    - [X] Categorizar slots com herança de tipos de item?

## Renderização
- [X] Escurecer blocos não-visitados
- [X] Método de animação mais organizado
    > - [ ] Superclasse
    > - [ ] Interface
    - [X] Composição
- [X] Criar versão melhorada da interface Renderable
    - [X] Superclasse com x, y, width e height?

## Geral código
- [X] Refatorar para utilizar esquema push-pop transform

## Outro
- [X] Subir pro Git
- [X] Passar essa lista para MarkDown