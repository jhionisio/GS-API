# GlobalSolution
APi para APP de cultivo, para analisar e mostrar que tipo de grão pode ser plantado.

# Endpoints
- Tipo solo
    - [Cadastrar](#cadastrar-tipo_solo)
    - Listar todos
    - Selecionar
    - Apagar
    - Atualizar
    - [Mostrar os detalhes](#detalhar-tipo_solo)
- Grao
    - [Cadastrar](#cadastrar-grao)
    - Listar todos
    - Apagar
    - Atualizar
    - [Mostrar os detalhes](#detalhar-grao)
- Tipo clima
    - [Cadastrar](#cadastrar-tipo_clima)
    - Listar todos
    - Apagar
    - Atualizar
    - [Mostrar os detalhes](#detalhar-tipo_clima)

---

### Cadastrar tipo solo
`POST` /global_solution/api/tipo_solo

| campo | tipo | obrigatório | descrição
|-------|------|-------------|----------
| ID_TIPO_SOLO | INT | sim | é o id de um tipo de solo previamente cadastrado
| NM_COR_SOLO | String | sim | é a cor do solo com no máximo 255 caracteres
| NM_TIPO_SOLO| String | sim | é o tipo de solo com no máximo 255 caracteres

**Exemplo de corpo do request**

```js
{
    "NM_COR_SOLO": "marrom"
    "NM_TIPO_SOLO": "argiloso"
}
```

**Código de resposta**

| código | descrição
|--------|----------
| 201 | tipo de solo cadastrado com sucesso
| 400 | erro na validação dos dados da requisição

---

### Detalhar tipo solo
`GET` /global_solution/api/tipo_solo{id}

**Exemplo de corpo de resposta**

```js
{
    "ID_TIPO_SOLO": 1,
    "NM_COR_SOLO": "beje",
    "NM_TIPO_SOLO": "arenoso"
}
```

**Códigos de Resposta**

| código | descrição
|--------|----------|
| 200 | dados retornados no corpo da resposta
| 404 | não foi possível encontrar o tipo de solo com o id informado

---

### Cadastrar grao
`POST` /global_solution/api/grao

| campo | tipo | obrigatório | descrição
|-------|------|-------------|----------
| ID_GRAO | INT | sim | é o id de um grão previamente cadastrado
| NM_GRAO | String | sim | é o nome do grão
| DS_GRAO| String | sim | é a descrição do grão

**Exemplo de corpo do request**

```js
{
    "NM_GRAO": "soja"
    "DS_GRAO": "grão de soja"
}
```

**Código de resposta**

| código | descrição
|--------|----------
| 201 | grão cadastrado com sucesso
| 400 | erro na validação dos dados da requisição

---

### Detalhar grao
`GET` /global_solution/api/grao{id}

**Exemplo de corpo de resposta**

```js
{
    "ID_GRAO": 1,
    "NM_GRAO": "soja",
    "DS_GRAO": "grao de soja"
}
```

**Códigos de Resposta**

| código | descrição
|--------|----------|
| 200 | dados retornados no corpo da resposta
| 404 | não foi possível encontrar o grão com o id informado

---

### Cadastrar tipo clima
`POST` /global_solution/api/tipo_clima

| campo | tipo | obrigatório | descrição
|-------|------|-------------|----------
| ID_TIPO_CLIMA | INT | sim | é o id de um tipo de clima previamente cadastrado
| DS_CLIMA | String | sim | é a descrição do clima com no máximo 255 caracteres

**Exemplo de corpo do request**

```js
{
    "DS_CLIMA": "clima mais seco"
}
```

**Código de resposta**

| código | descrição
|--------|----------
| 201 | tipo de clima cadastrado com sucesso
| 400 | erro na validação dos dados da requisição

---

### Detalhar tipo clima
`GET` /global_solution/api/tipo_clima{id}

**Exemplo de corpo de resposta**

```js
{
    "ID_TIPO_CLIMA": 1,
    "DS_CLIMA": "seco",
}
```

**Códigos de Resposta**

| código | descrição
|--------|----------|
| 200 | dados retornados no corpo da resposta
| 404 | não foi possível encontrar o tipo de clima com o id informado
