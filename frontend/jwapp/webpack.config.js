var path = require('path');
var ngAnnotatePlugin = require('ng-annotate-webpack-plugin');
module.exports={
  entry: './src/jwApp.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist/assets/js')
  },
  module: {
        rules : [
        {
          test: /\.html$/,
          loader: 'raw-loader'
        }
    ]
  },
  devServer: {
    hot: true,
    inline: true,
    stats: "errors-only",
    port: 8080
  },
  // devtool : 'source-map',
  plugins: [
    new ngAnnotatePlugin()
  ]
}