package com.example

import history.Location
import kotlinx.js.Record
import org.w3c.dom.url.URLSearchParams
import react.ChildrenBuilder
import react.VFC
import react.create
import react.dom.html.ButtonType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.code
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.pre
import react.dom.html.ReactHTML.ul
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.HashRouter
import react.router.dom.Link
import react.router.dom.useSearchParams
import react.router.useLocation
import react.router.useParams

private const val USE_LOCATION_PATH = "/useLocationExample"

private const val USE_SEARCH_PATH = "/useSearchExample"

private const val USE_PARAMS_PATH = "/useParamsExample"

private fun ChildrenBuilder.catchIfNecessary(block: ChildrenBuilder.() -> Unit) =
    try {
        block()
    } catch (t: Throwable) {
        pre {
            +t.stackTraceToString()
        }
    }

private val UseLocationExample = VFC {
    h1 {
        code {
            +"useLocation()"
        }

        +" example"
    }

    div {
        catchIfNecessary {
            val location: Location = useLocation()

            ul {
                li {
                    +"pathname: \"${location.pathname}\""
                }
                li {
                    +"search: \"${location.search}\""
                }
                li {
                    +"hash: \"${location.hash}\""
                }
            }
        }
    }
}

private val UseSearchParamsExample = VFC {
    h1 {
        code {
            +"useSearchParams()"
        }

        +" example"
    }

    div {
        catchIfNecessary {
            val (search: URLSearchParams, _) = useSearchParams()

            ul {
                li {
                    +"search (raw): \"$search\""
                }
                li {
                    +"key1: ${search.get("key1")}"
                }
                li {
                    +"key2: ${search.get("key2")}"
                }
            }
        }
    }
}

private val UseParamsExample = VFC {
    h1 {
        code {
            +"useParams()"
        }

        +" example"
    }

    div {
        catchIfNecessary {
            val params: Record<String, String> = useParams()
            ul {
                li {
                    +"key1: ${params["key1"]}"
                }
                li {
                    +"key2: ${params["key2"]}"
                }
            }
        }
    }
}

private val RootMenu = VFC {
    div {
        h1 {
            +"Root menu"
        }

        ul {
            li {
                Link {
                    to = USE_LOCATION_PATH

                    button {
                        type = ButtonType.button

                        code {
                            +"useLocation()"
                        }

                        +" example (no parameters)"
                    }
                }
            }

            li {
                Link {
                    to = "$USE_LOCATION_PATH?key1=value1&key2=value2#value3"

                    button {
                        type = ButtonType.button

                        code {
                            +"useLocation()"
                        }

                        +" example (custom parameters)"
                    }
                }
            }

            li {
                Link {
                    to = USE_SEARCH_PATH

                    button {
                        type = ButtonType.button

                        code {
                            +"useSearchParams()"
                        }

                        +" example (no parameters)"
                    }
                }
            }

            li {
                Link {
                    to = "$USE_SEARCH_PATH?key1=value1&key2=value2"

                    button {
                        type = ButtonType.button

                        code {
                            +"useSearchParams()"
                        }

                        +" example (custom parameters)"
                    }
                }
            }

            li {
                Link {
                    to = USE_PARAMS_PATH

                    button {
                        type = ButtonType.button

                        code {
                            +"useParams()"
                        }

                        +" example (no parameters)"
                    }
                }
            }

            li {
                Link {
                    to = "$USE_PARAMS_PATH/value1/value2"

                    button {
                        type = ButtonType.button

                        code {
                            +"useParams()"
                        }

                        +" example (custom parameters)"
                    }
                }
            }
        }

        hr()

        Routes {
            Route {
                path = USE_LOCATION_PATH
                element = UseLocationExample.create()
            }

            Route {
                path = USE_SEARCH_PATH
                element = UseSearchParamsExample.create()
            }

            Route {
                path = USE_PARAMS_PATH
                element = UseParamsExample.create()
            }

            Route {
                path = "$USE_PARAMS_PATH/:key1/:key2"
                element = UseParamsExample.create()
            }
        }
    }
}

/**
 * The main entry point.
 */
internal val BrowserRouterTest = VFC {
    BrowserRouter {
        RootMenu()
    }
}

/**
 * An alternative entry point.
 */
internal val HashRouterTest = VFC {
    HashRouter {
        RootMenu()
    }
}
