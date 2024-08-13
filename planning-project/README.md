There will only be a user role in this app. No other roles.
APP NAME :
WEDER

## Casual User Story

- As a casual user I will be able to go to the site and check out the home page and see a welcome UI.
- I will be able to select the weather tab and load its UI.
- I will be able to enter the info for one location at a time of my location of choice.
- I will be able to see the weather of that location once entered.
- I will be able to go the the log in tab to create or login.

## Authenticated User Story

- As an authenticated user I will be able to login.
- I will be able to view all the pages on the site.
- I will be able to go to the home page and see its UI.
- I will be able to see my favorited location's weather in my home page.
- I will be able to see my name in the navbar
- I will be able to visit the weather page is the same UI as above
- I will be able to see the "save button" in the weather page which will allow the weather to be saved in my weather preferences tab.
- I will be able to save up to 3 preferences at a time
- I will be able to visit the weather preferences tab
- I will be able to view my saved preferences
- I will be able to select my favorite weather preference(loads in home page)
- I will be able update a preference location
- I will be able to delete a preference all together.
- I will be able to see the clothing preference page
- I will allow me to set my preferences of clothing depending on weather. For example I will be able to input that my sweater is used for certain weather conditions. My raincoat only for when its raining, and so on depending on the clothes I want to input.
- I will be able to see the outfit page.
- I will be able to see a randomized outfit based on my clothin preferences and weather preferences.
- I will not be able to do any CRUD on here, all UI is based on other pages.
- I will be able to update outfit based on weather location chosen.

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
