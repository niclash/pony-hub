# PonyHub
PonyHub is a search service for Pony Language libraries and applications.

## Website
On the web site it is possible for humans to free-text search projects for words and
register new projects.

## API
There is also a programmatic API available for tooling to use the service. The following
API endpoints are available;

## Abbreviated hosts
Since most projects are expected to be on GitHub, `github.com` is internally shortened to
`gh`, to shorten identifiers and such. Once GitLab is supported, `gl` is going to be used
for that. Other abbreviations might surface over time.

### `/p/` - Projects
 
1. GET a project with all the data is kept about it on PonyHub. The identifier for the
   project is passed in the URL as `/p/{host}/{org}/{name}/{version}`. If no `{version}` 
   is given, the default branch will be used as the tag.
   
1. POST a 'locator' and the project will be scanned for information. `x-www-form-urlencoded`
   as content type, and `locator={value}` in the body.

### `/s/` - Search
1. POST to search. Currently only free-text search is available, but more will follow.
Pass the search string with a prepended "free:".

### `/st/` - Statistics
Primarily for internal use, and may change drastically without notice.

### `/ov/` - Overview
1. GET the recent updates and the stats on what the system is storing. 
