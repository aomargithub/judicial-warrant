module.exports = function(app){
    app.factory('menuItemsFcty', function($rootScope, appRoleFcty){

        function Item(){

            var subItemsValue = [];
            var selfShowFilter = null;
            this.codeValue = function(code){
                this.code = code;
                this.title = $rootScope.messages[this.code];
                return this;
            };

            this.classesValue = function(classes){
                this.classes = classes;
                return this;
            };

            this.selfShowFilterValue = function(selfShowFilterValue){
                selfShowFilter = selfShowFilterValue;
                return this;
            };

            this.hasSubItems = function(){
                return selfShowFilter === null;
            }

            this.routeValue = function(route){
                this.route = route;
                return this;
            };

            this.addSubItem = function(subItem){
                subItemsValue.push(subItem);
                return this;
            };

            this.showFilter = function(role){
               
                if(selfShowFilter){
                    return selfShowFilter(role);
                }

                this.subItems = subItemsValue.filter(function(subItem){
                    return subItem.showFilter(role);
                });
                
                return this.subItems.length > 0? true : false;
            };
        }

        function SubItem(){
            
            this.codeValue = function(code){
                this.code = code;
                this.title = $rootScope.messages[this.code];
                return this;
            };

            this.routeValue = function(route){
                this.route = route;
                return this;
            }

            this.showFilterValue = function(showFilter){
                this.showFilter = showFilter;
                return this;
            };
        }
//========================================
        var homeItem = new Item();
        homeItem.codeValue('home').routeValue('home').selfShowFilterValue(function(role){return true;}).classesValue(["glyphicon", "glyphicon-home"]);
        
        var requestsItem = new Item();
        requestsItem.codeValue('requests').routeValue('.requests').selfShowFilterValue(function(role){return role === appRoleFcty.officer.code;}).classesValue(["glyphicon", "glyphicon-envelope"]);
        
        var myRequestsItem = new Item();
        myRequestsItem.codeValue('myRequests').routeValue('.myRequests').selfShowFilterValue(function(role){return role === appRoleFcty.user.code;}).classesValue(["glyphicon", "glyphicon-envelope"]);
        
        var trainingEntitledRegistrationsItem = new Item();
        trainingEntitledRegistrationsItem.codeValue('requestEntitledRegistrations').routeValue('.requestEntitledRegistrations').selfShowFilterValue(function(role){return role === appRoleFcty.training.code;}).classesValue(["glyphicon", "glyphicon-envelope"]);
       
 //===================================



        var lookupsItem = new Item();
        lookupsItem.codeValue('LOOKUPS').routeValue('lookupsSubmenu').classesValue(["glyphicon", "glyphicon-cog"]);

        var organizationUnitsSubItem = new SubItem(), 
        attachmentTypesSubItem = new SubItem(),
        requestTypeAttachmentTypesSubItem = new SubItem();
      
       

        organizationUnitsSubItem.codeValue('organizationUnits')
        .routeValue('.organizationUnits')
        .showFilterValue(function(role){
            return role === appRoleFcty.admin.code;});

        attachmentTypesSubItem.codeValue('attachmentTypes')
        .routeValue('.attachmentTypes')
        .showFilterValue(function(role){return role === appRoleFcty.officer.code;});

        requestTypeAttachmentTypesSubItem.codeValue('requestTypeAttachmentTypes')
        .routeValue('.requestTypeAttachmentTypes')
        .showFilterValue(function(role){return role === appRoleFcty.officer.code;});

       





        
        
        lookupsItem.addSubItem(organizationUnitsSubItem)
                   .addSubItem(attachmentTypesSubItem)
                   .addSubItem(requestTypeAttachmentTypesSubItem);
                  
                   


        
         //===================================
        var usersItem = new Item()
        usersItem.codeValue('USERS').routeValue('usersSubmenu').classesValue(["glyphicon", "glyphicon-user"]);
        var internalUsersSubItem = new SubItem(),
        externalUsersSubItem = new SubItem();


        internalUsersSubItem.codeValue('internalUsers')
        .routeValue('.internalUsers')
        .showFilterValue(function(role){
            return role === appRoleFcty.admin.code;
        });

        externalUsersSubItem.codeValue('externalUsers')
        .routeValue('.externalUsers')
        .showFilterValue(function(role){
            return role === appRoleFcty.admin.code;
        });


        usersItem.addSubItem(internalUsersSubItem)

        .addSubItem(externalUsersSubItem);

        var menu = [homeItem,requestsItem, myRequestsItem,trainingEntitledRegistrationsItem, lookupsItem, usersItem];

        return {
            items :  menu
        };
    });
};