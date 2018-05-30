module.exports = function (app) {
    app.factory('breadcrumbsFcty', function ($state, breadcrumbsConfig) {

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
                        breadcrumbs.push(parentStates[i]);
                    }
                }
            } else {
                breadcrumbs = parentStates;
            }
            // add current state to breadcrumbs
            breadcrumbs.push($state.current);
            return breadcrumbs;
        };
        return {
            getbreadcrumbs: function (abstract) {
                return createBreadcrumbs(abstract);
            }
        };
    });
};