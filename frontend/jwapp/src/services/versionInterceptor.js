module.exports = function (app) {
    app.factory('versionInterceptor', function () {
        var versionInterceptor = {
            request: function (config) {
                if (config.method === 'PUT') {
                    var data = config.data;

                    if (data.version) {
                        config.headers["If-Match"] = data.version;
                    }
                }


                return config;
            }
        };

        return versionInterceptor;
    });
};