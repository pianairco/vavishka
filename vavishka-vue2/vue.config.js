// vue.config.js
module.exports = {
    outputDir: '../src/main/resources/public',
    runtimeCompiler: true,
    chainWebpack: config => {
        config.module.rules.delete('eslint');
    },
    devServer: {
        port: 80,
        proxy: {
            '/api': {
                target: 'http://localhost:8081',
                changeOrigin: true,
                secure: false,
                pathRewrite: {
                    '^/api': '/api'
                }
            },
            '/cdn': {
                target: 'http://localhost:8081',
                changeOrigin: true,
                secure: false,
                pathRewrite: {
                    '^/cdn': '/'
                }
            }
        }
    }
}
