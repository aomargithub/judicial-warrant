module.exports = function(app){
    app.directive('breadcrumbs', function(breadcrumbsFcty){
        return {
            restrict: 'E',
            transclude: true,
            template : require('./breadcrumbs-drtv.html'),
            link: function(scope, element, attrs) {
                var abstract, render;
                attrs.abstract = attrs.abstract ? attrs.abstract : false;
                abstract = JSON.parse(attrs.abstract);
                render = function() {
                  if (scope.$breadcrumbs !== breadcrumbsFcty.getbreadcrumbs(abstract)) {
                    scope.$breadcrumbs = breadcrumbsFcty.getbreadcrumbs(abstract);
                  }
                };
                // once the view content is updated
                // breadcrumbs are updated
                scope.$on('$viewContentLoaded', function() {
                  render();
                });
              }         
        };
    });
};  