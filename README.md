# Information_retrieval

## Search Personalization
* Topics can be marked as interesting by the user and causes a re-ranking of the results if the user is searching for the same query or if a new search is performed.
* The task was to create web search engine using Solr, parses and indexes .txt and .jpg documents that a given folder contains and to list top 10 relevant files given a query.
* Dataset created using the `download.py` file. The result obtained is a `json` file compatible for combining all the documents and types.
* After indexing the file by Solr, the user can enter their query and search query and the top relevant results will be shown on the screen with the rank assigned to the document.

## Technologies used during the development

### Frontend
1. Angular
2. Apache Solr

### Backend
1. Java
2. Python
3. Lucene-8.7.0

## Angular project

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 11.0.6.

### Code scaffolding
Run `ng generate` component `component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Development server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Build
Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Running unit tests
Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io/).

### Running end-to-end tests
Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help
To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference page](https://angular.io/cli).
