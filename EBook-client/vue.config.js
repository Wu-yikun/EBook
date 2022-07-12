module.exports = {
    devServer: {
        // 前端项目启动端口
        port: 3000,
        // 跨域代理
        proxy: {
            '/api': {
                // 后端项目启动端口
                target: 'http://localhost:8080/',
                // target: 'http://139.196.99.129:8080/',   // Aliyun 服务器地址
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
        },
        // 避免内网穿透出现问题: Invalid Host Header
        disableHostCheck: true
    },
    // 基本路径
    publicPath: './',
}
