There will only be a user role in this app. No other roles.

## Casual User Story

- As a casual user I will be able to go to the site and check out the home page and weather page. I will be able to check the weather for one location at a time of my location of choice. In the home page I will be able to see the name and the UI(decided later)I go to the weather page, enter my location and it will show weather details to be decided by the developer(me).

## Authenticated User Story

- As an authenticated user I will be able to view all the pages on the site. Home page, weather page, saved weather page, clothing preferences and clothing page. The home page is different, my main saved weather preference will be displayed to show me the weather of my favorited location. The weather page is the same as above but I will be able to see the "save button" which will allow the weather to be saved in my saved weather page. In this page I will be able to view my saved preferences, select my favorite (loads in home page), update a preference location or delete a preference all together. My clothing preference page will allow me to set my preferences of clothing depending on weather. For example I will be able to input that my sweater is used for certain weather conditions. My raincoat only for when its raining, and so on depending on the clothes I want to input. The last page will showcase which outfit to put on depending on the weather location I have set to my favorite. This page will not have any modifications since it is random, although I can change my favorited location which will change the outcome but that is not done in this page.

## TECHNOLOGY

- the database (tables and relationships)

  - User Collection
    - User id
    - User name
    - User username
    - User password
  - Location Collection
    - Location id
    - Latitude
    - Longitude
    - City
    - State
    - Country Abbr ? debating
  - Weather Collection
    - Weather id
    - Location Id
    - Rain (is raining or not with tinyInt 1 or 0)
    - Temperature Degrees
    - Temperature Measurement (F or C only)
    - Cloudy ( 1 or 0)
    - Sunny ( 1 or 0)
  - Weather Preferences Collection
    - Weather id
    - User id
  - Clothing Item Collection
    - PK id
    - User id
    - Clothing Item
    - Clothing type ( shirt pants shoes etc)
    - Clothing img
    - For Rain (y or n)
    - For Hot or Cold
  - Clothing Outfit Collection
    - PK id
    - Clothing Outfit (formatter)
    - Outfit img

- the REST API (models, repositories, services, controllers)
  - Models
    - User
    - Location
    - Weather
    - Weather Preference
    - Clothing Item
    - Clothing Outfit
  - Repositories
    - For each model
  - Services
    - For each repo
    - Result
    - Result Type
  - Controllers
    - For each services
    - Global err handling
- the UI (components and their relationships) - React Routers for each one except location below - Components - User - Weather (Location small component inside this one) - Preferences - Clothing - Outfit
  other details (complex rules, calculations, challenging technologies) - Updated home page - updated in real time weather with images. - isRaining trumps all other conditions. - Switch case for image background
  ex: isRaining ? rainingImg() : otherWeatherImg()

## Tasks

**BackEnd**

- Set up (1 hour)
  - MongoDb (30 min)
  - Intellij (30 min)
- Layers
  - TESTING MOCKITO (3 hours total)
    - Data Layer testing (1.5 hours)
    - Domain Layer testing (1.5 hours)
  - Models (30 min total)
    - User
    - Location
    - Weather
    - Weather Preference
    - Clothing Item
    - Clothing Outfit
  - Data Layer (30 min each)
    - Repositories(Using Mongo spring default methods)
    - Interface implements mongoDB repo
      - Label Collections
        - User
        - Location
        - Weather
        - Weather Preference
        - Clothing Item
        - Clothing Outfit
  - Service Layer (1 hour each)
    - User
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
    - Location
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
    - Weather
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
    - Weather Preference
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
        - findAll()
        - count()
    - Clothing Item
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
    - Clothing Outfit
      - Validation
      - MongoDB Methods
        - add(repo.save())
        - updated(repo.save())
        - delete (repo.deleteById())
        - findById()
  - Controller Layer ( 30 min each)
    - Implement Service Methods
    - User
    - Location
    - Weather
    - Weather Preference
    - Clothing Item
    - Clothing Outfit
    - Main App
- Connecting to FrontEnd
  - CORS

**FrontEnd**

- Set up (1 hour)
  - Npm commands, organize packages, imports, github
- Components (1 hour each)

  - User (UseContext)
  - NavBar
  - Home
    - import <Weather/>
  - Weather
    - Location inside weather
  - Weather Preference
  - Clothing Item
  - Clothing Outfit

  TIMELINE FRIDAY EARLIEST
