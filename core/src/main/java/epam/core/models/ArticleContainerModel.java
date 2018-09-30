package epam.core.models;

import epam.core.models.dto.Article;
import epam.core.services.ArticleService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model(adaptables = Resource.class)
public class ArticleContainerModel {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleContainerModel.class);
    private static final String ARTICLE_SOURCE_DEFAULT = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=80b6529f98b843419195ea022760eeb0";

    @ValueMapValue(optional = true)
    private String title;

    @ValueMapValue(optional = true)
    @Default(values = ARTICLE_SOURCE_DEFAULT)
    private String source;

    @OSGiService
    private ArticleService articleService;

    public String getTitle() {
        return title;
    }

    public List<Article> getArticleList() {
        LOG.error("Error occur when pulling artiles from {}", articleService.getArticlesByUrl(source).get(0).toString());
        return articleService.getArticlesByUrl(source);
    }
}
