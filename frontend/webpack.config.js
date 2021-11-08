var HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require("path");

module.exports = {
    devtool: 'inline-source-map',
    entry: "./src/index.js",
    output: {
        path: path.join(__dirname, '/dist'),
        filename: 'index_bundle.js'
    },
    mode: 'development',
    resolve: {
        extensions: ['.js', '.jsx'],
        modules: [__dirname, 'src/public', 'node_modules']
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            // {
            //     test: /\.(svg|png|jpg|jpeg|gif)$/,
            //     use: {
            //         loader: "file-loader",
            //         options: {
            //             name: "[name].[hash].[ext]",
            //             outputPath: "imgs"
            //         }
            //     }
            // },
            {
                test: /\.(png|jpg|gif|svg|jpeg)$/i,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 163280,
                        },
                    },
                ],
            }
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: './src/index.html',
        favicon: './src/public/favicon/favicon-32x32.png',
    })],
    devServer: {
        historyApiFallback: true
    },
    externals: {
        // global app config object
        config: JSON.stringify({
            apiUrl: 'http://localhost:8081'
        })
    }
}