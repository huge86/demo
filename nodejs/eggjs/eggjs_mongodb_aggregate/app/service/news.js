'use strict';

const Service = require('egg').Service;

class NewsService extends Service {
  async getNewsList() {

    return [

        {
            title:'新闻111'
        },
        {
            title:'新闻222'
        },
        {
            title:'新闻333'
        }
    ]
  }
}

module.exports = NewsService;
