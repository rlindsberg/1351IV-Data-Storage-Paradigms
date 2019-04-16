let $langs := distinct-values(for $lang in //guides/guide/languages/language/name
return $lang)
for $lang in $langs
return <Language name="{$lang}"/>