package com.github.thogr.bedede;

import org.openqa.selenium.WebElement;

import com.github.thogr.bedede.Bedede;
import com.github.thogr.bedede.Framework;
import com.github.thogr.bedede.GivenElement;
import com.github.thogr.bedede.conditions.Expecting;

public final class BededeSelenium extends Bedede {

    private BededeSelenium(final Framework only) {
        super(only);
    }

    public static GivenElement<WebElement> given(final Expecting<?> exp) {
        return GivenElement.<WebElement>given(exp);
    }


}
