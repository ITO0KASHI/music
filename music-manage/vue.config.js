const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  chainWebpack: config => {
    config.plugin('define').tap(definitions => {
        Object.assign(definitions[0]['process.env'], {
          NODE_HOST: '"http://10.100.40.246:8888"',
        });
        return definitions;
    });
  },

  devServer: {
    host: '0.0.0.0',
    port: 8081,
}
})
