module.exports = function (app) {
    app.provider('breadcrumbsConfig', function () {
        var abstract;
        abstract = false;
        return {
            setAbstract: function (value) {
                // parse string to boolean
                // if the input is not a boolean
                value = JSON.parse(value);
                abstract = value;
            },
            $get: function () {
                return abstract;
            }
        };
    });
}