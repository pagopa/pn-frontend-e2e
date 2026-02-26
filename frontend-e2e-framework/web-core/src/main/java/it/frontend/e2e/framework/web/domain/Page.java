package it.frontend.e2e.framework.web.domain;

import it.frontend.e2e.framework.web.annotation.PageInfo;

public interface Page extends Component{
    default String getUrl() {
        PageInfo pageInfo = this.getClass().getAnnotation(PageInfo.class);
        if (pageInfo == null) {
            throw new IllegalStateException("Page class must be annotated with @PageInfo");
        }
        return pageInfo.url();
    }
}
