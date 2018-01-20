Feature: I want to open Takealot website

  #Scenario: Add cart
    #Given I had the takealot web address
    #And I open the takealot website
    #And the initial cart having "0" items
    #When I search for "Macbook"
    #Then the list of "MacBook"s are displayed
    #When I select the model "MK4N2"
    #Then the "MacBook" of the model "MK4N2" is displayed
    #And I click on "Add to Cart" button
    #And the item is added to the cart
    #And I close the browser
    #
    
    Scenario Outline: Opening website
    
    Given I open the "<webAddress>" website
    And I close the browser
    
   Examples:
   	| webAddress |
   	| http://www.facebook.com |
   	| http://www.takealot.com |
   	| http://www.makro.co.za |
