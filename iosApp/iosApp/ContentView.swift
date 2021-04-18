//
//  ContentView.swift
//  iosApp
//
//  Created by Jose Flavio Quispe on 18/04/21.
//

import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel

    // TODO: much to improve
    var body: some View {
        NavigationView {
            listView()
                .navigationBarTitle("SpaceX Launches")
                .navigationBarItems(trailing:
                                        Button("Reload") {
                                            self.viewModel.loadLaunches(forceReload: true)
                                        })
        }
    }
    
    private func listView() -> AnyView {
        switch viewModel.launches {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let launches):
            return AnyView(List(launches, id: \.flightNumber) { launch in
                RocketLaunchRow(rocketLaunch: launch)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel(repository: SpaceXRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory())))
    }
}

extension ContentView {
    
    enum LoadableLaunches {
        case loading
        case result([RocketLaunch])
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        let repository: SpaceXRepository
        @Published var launches = LoadableLaunches.loading
        
        init(repository: SpaceXRepository) {
            self.repository = repository
            self.loadLaunches(forceReload: false)
        }
        
        func loadLaunches(forceReload: Bool) {
            self.launches = .loading
            repository.getLaunches(forceReload: forceReload, completionHandler: { launches, error in
                if let launches = launches {
                    self.launches = .result(launches)
                } else {
                    self.launches = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}
