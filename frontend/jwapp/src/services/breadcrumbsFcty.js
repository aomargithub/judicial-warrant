module.exports = function (app) {
    app.factory('breadcrumbsFcty', function ($state, breadcrumbsConfig,$rootScope) {

        var createBreadcrumbs = function (abstract) {

            var breadcrumbs, i, parentStates, stateArray;
            abstract = abstract ? abstract : breadcrumbsConfig;
            breadcrumbs = [];
            stateArray = [];
            parentStates = [];
            // gets all states
            for (i in $state.$current.includes) {
                stateArray.push(i);
            }
            // get parent state details
            for (i in stateArray) {
                if (stateArray[i] !== '') {
                    if ($state.get(stateArray[i]).parent && $state.get(stateArray[i]).parent.name !== '') {
                        parentStates.push($state.get(stateArray[i]).parent);
                    }
                }
            }
            // if abstract is false
            // removes abstract states from breadcrumbs
            if (!abstract) {
                for (i in parentStates) {
                    if (!parentStates[i].abstract) {
                        breadcrumbs.push({"state":parentStates[i] , "name" : $rootScope.messages[parentStates[i]]});
                    }
                }
            } else {
                breadcrumbs = parentStates;
            }
            // add current state to breadcrumbs
            breadcrumbs.push({"state":$state.current , "name" : $rootScope.messages[$state.current.data.label]});
            return breadcrumbs;
        };
        return {
            getbreadcrumbs: function (abstract) {
                return createBreadcrumbs(abstract);
            }
        };
    });
};